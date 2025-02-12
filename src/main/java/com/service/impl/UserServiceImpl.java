package com.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bo.AuthRequest;
import com.bo.AuthResponse;
import com.bo.Response;
import com.dao.ObjectDao;
import com.exceptions.NotFoundException;
import com.helper.AppConstants;
import com.helper.CommonMessages;
import com.helper.ErrorConstants;
import com.model.Address;
import com.model.CommonAppSetting;
import com.model.ProfilePicData;
import com.model.SystemUser;
import com.model.SystemUserRole;
import com.model.User;
import com.model.UserWiseRoles;
import com.service.ProfilePicDataService;
import com.service.UserService;
import com.utils.FileUtility;
import com.utils.RandomCreator;

@Service
public class UserServiceImpl implements UserService {

	private final ObjectDao objectDao;

	private final PasswordEncoder passwordEncoder;

	private final FileUtility fileUtility;

	private final ProfilePicDataService profilePicService;

	public UserServiceImpl(ObjectDao objectDao, PasswordEncoder passwordEncoder, FileUtility fileUtility,
			ProfilePicDataService profilePicService) {
		this.objectDao = objectDao;
		this.passwordEncoder = passwordEncoder;
		this.fileUtility = fileUtility;
		this.profilePicService = profilePicService;
	}

	@Override
	// For Adding user firstName,lastName,birthDate,email,password,mobile is must
	public Response addUser(User user) throws Exception {
		Response response = new Response();
		try {
			if (userNullChecker("ADD", user)) {
				User existingUserByEmail = objectDao.getObjectByParam(User.class, "email", user.getEmail());
				if (null != existingUserByEmail) {
					response.setStatus(ErrorConstants.ALREADY_EXIST);
					response.setMessage("Email Already Registerd Try Again With Another");
					return response;
				}

				User existingUserByMobile = objectDao.getObjectByParam(User.class, "mobile", user.getMobile());
				if (null != existingUserByMobile) {
					response.setStatus(ErrorConstants.ALREADY_EXIST);
					response.setMessage("Mobile Number Already Registerd Try Again With Another");
					return response;
				}
				if (null != user.getAddress()) {
					Address address = new Address();
					address.setStreet(user.getAddress().getStreet());
					address.setCity(user.getAddress().getCity());
					address.setState(user.getAddress().getState());
					address.setCountry(user.getAddress().getCountry());
					address.setZipCode(user.getAddress().getZipCode());
					objectDao.saveObject(address);
					user.setAddress(address);
				}
				user.setUserType(AppConstants.NORMAL_USER);
				user.setIsActive(true);
				user.setUserUniqueUID(RandomCreator.generateUID(AppConstants.SYSTEM_USER_PREFIX, 8));
				user.setPassword(passwordEncoder.encode(user.getPassword()));

				objectDao.saveObject(user);
				response.setStatus(ErrorConstants.SUCESS);
				response.setMessage("User Added Sucessfully");
				response.setResult(user.getUserId());

			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	public static Boolean userNullChecker(String operation, User user) {
		if (operation.equalsIgnoreCase("EDIT")) {
			return null != user && null != user.getUserId();
		}
		return null != user && null != user.getEmail() && null != user.getPassword() && null != user.getMobile()
				&& null != user.getFirstName() && null != user.getLastName() && null != user.getBirthDate();

	}

	@Override
	public Response editUser(User user) throws Exception {
		Response response = new Response();
		try {
			if (userNullChecker("EDIT", user)) {
				User existingUser = objectDao.getObjectById(User.class, user.getUserId());
				if (null != existingUser) {
					if (null != user.getEmail()) {
						User exstingUserByEmail = objectDao.getObjectByParam(User.class, "email", user.getEmail());
						if (null != exstingUserByEmail && existingUser.getUserId() != exstingUserByEmail.getUserId()) {
							response.setStatus(ErrorConstants.ALREADY_EXIST);
							response.setMessage("Email Already Registered Try With Another");
							return response;
						} else {
							existingUser.setEmail(user.getEmail());
						}
					}
					if (null != user.getMobile()) {
						User exstingUserByMobile = objectDao.getObjectByParam(User.class, "mobile", user.getMobile());
						if (null != exstingUserByMobile
								&& existingUser.getUserId() != exstingUserByMobile.getUserId()) {
							response.setStatus(ErrorConstants.ALREADY_EXIST);
							response.setMessage("Mobile Number Already Registered Try With Another");
							return response;
						} else {
							existingUser.setMobile(user.getMobile());
						}
					}

					if (user.getBirthDate() != null) {
						existingUser.setBirthDate(user.getBirthDate());
					}
					if (user.getFirstName() != null) {
						existingUser.setFirstName(user.getFirstName());
					}
					if (user.getLastName() != null) {
						existingUser.setLastName(user.getLastName());
					}

					if (null != user.getAddress()) {
						if (null != user.getAddress().getAddressId()) {
							Address existingAddress = objectDao.getObjectById(Address.class,
									user.getAddress().getAddressId());
							if (existingAddress != null) {
								if (user.getAddress().getStreet() != null) {
									existingAddress.setStreet(user.getAddress().getStreet());
								}
								if (user.getAddress().getCity() != null) {
									existingAddress.setCity(user.getAddress().getCity());
								}
								if (user.getAddress().getState() != null) {
									existingAddress.setState(user.getAddress().getState());
								}
								if (user.getAddress().getCountry() != null) {
									existingAddress.setCountry(user.getAddress().getCountry());
								}
								if (user.getAddress().getZipCode() != null) {
									existingAddress.setZipCode(user.getAddress().getZipCode());
								}
								objectDao.updateObject(existingAddress);
								existingUser.setAddress(existingAddress);
							}
						} else {
							Address address = new Address();
							address.setStreet(user.getAddress().getStreet());
							address.setCity(user.getAddress().getCity());
							address.setState(user.getAddress().getState());
							address.setCountry(user.getAddress().getCountry());
							address.setZipCode(user.getAddress().getZipCode());
							objectDao.saveObject(address);
							existingUser.setAddress(address);
						}
					}
					objectDao.updateObject(existingUser);
					fileUtility.saveProfileImages(user);
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("User Updated Sucessfully");

				} else {
					response.setStatus(ErrorConstants.NOT_FOUND);
					response.setMessage("User Not Found with id:" + user.getUserId());
				}

			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response getUserDetailsById(Long userId) throws Exception {
		Response response = new Response();
		try {
			if (null != userId && userId > 0) {
				User user = objectDao.getObjectById(User.class, userId);
				ProfilePicData currentProfilePic = profilePicService.getUserCurrentProfilePic(user);
				if (null != currentProfilePic && null != currentProfilePic.getProfilePicPath()) {
					CommonAppSetting profileFolder = objectDao.getObjectByParam(CommonAppSetting.class, "settingName",
							AppConstants.PROFILE_IMAGE_FOLDER);
					if (null != profileFolder && null != profileFolder.getSettingValue()) {
						user.setProfileImageUrl(
								profileFolder.getSettingValue() + currentProfilePic.getProfilePicPath());
					}
				}
				if (null != user) {
					user.setPassword(null);
					response.setResult(user);
					response.setStatus(ErrorConstants.SUCESS);
					response.setMessage("User get sucessfully..");
				} else {
					response.setStatus(ErrorConstants.NOT_FOUND);
					response.setMessage("User Not Found");
				}
			} else {
				response.setStatus(ErrorConstants.BAD_REQUEST);
				response.setMessage(CommonMessages.REQUIRED_FIELD_MISSING);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Response addMultipleUser(List<User> userList) throws Exception {
		Response response = new Response();
		Long sucessCount = 0L;
		Long errorCount = 0L;
		if (null != userList && userList.size() > 0) {
			for (User user : userList) {
				try {
					response = addUser(user);
					if (response.getStatus() == ErrorConstants.SUCESS) {
						sucessCount++;
					} else {
						errorCount++;
					}
				} catch (Exception e) {

				}
			}
		}

		response.setResult(null);
		response.setStatus(ErrorConstants.SUCESS);
		response.setMessage("Users Added Sucessfully...sucess(" + sucessCount + "),failure(" + errorCount + ")");
		return response;

	}

	@Override
	public List<SystemUserRole> allSystemRoles() throws Exception {
		List<SystemUserRole> allRoles = null;
		try {
			allRoles = objectDao.getAllRecords(SystemUserRole.class);
		} catch (Exception e) {
			throw e;
		}
		return allRoles;
	}

	@Override
	public com.bo.AuthResponse systemUserLogin(AuthRequest authRequest) throws Exception {
		AuthResponse response = new AuthResponse();

		// Validate input
		if (authRequest == null || authRequest.getEmail() == null || authRequest.getPassword() == null) {
			throw new NotFoundException("Email or Password is Missing...");
		}

		if (authRequest.getSystemUserRoleId() == null || authRequest.getSystemUserRoleId() <= 0) {
			throw new NotFoundException("Please Select Role...");
		}

		// Fetch the existing system user by email
		SystemUser existingSystemUserByEmail = objectDao.getObjectByParam(SystemUser.class, "email",
				authRequest.getEmail());
		if (existingSystemUserByEmail == null) {
			throw new NotFoundException("Email Not Found...");
		} else {
			// IF user present with given Email the want to check login attampts if exiads
			// limit then will throgh exception

			Integer maxLoginFailedAttemptAllowed;
			CommonAppSetting maxLoginAttamptCountSetting = objectDao.getObjectByParam(CommonAppSetting.class,
					"settingName", AppConstants.MAX_FAILED_LOGIN_ATTEMPTS_ALLOWED);
			if (null != maxLoginAttamptCountSetting && maxLoginAttamptCountSetting.getSettingValue() != null) {
				maxLoginFailedAttemptAllowed = Integer.parseInt(maxLoginAttamptCountSetting.getSettingValue());
			} else {
				maxLoginFailedAttemptAllowed = AppConstants.THREE;
			}

			existingSystemUserByEmail.setFailedLoginAttempt(existingSystemUserByEmail.getFailedLoginAttempt() != null
					? existingSystemUserByEmail.getFailedLoginAttempt()
					: AppConstants.ZERO);

			if (maxLoginFailedAttemptAllowed <= existingSystemUserByEmail.getFailedLoginAttempt()) {

				LocalDateTime currentDateTime = LocalDateTime.now();
				if (existingSystemUserByEmail.getIdLockExpirationTime() != null
						&& existingSystemUserByEmail.getIdLockExpirationTime().isBefore(currentDateTime)) {
					existingSystemUserByEmail.setFailedLoginAttempt(AppConstants.ZERO);
					existingSystemUserByEmail.setIdLockExpirationTime(null);
					objectDao.updateObject(existingSystemUserByEmail);

				} else {
					LocalDateTime lockExpirationTime;
					CommonAppSetting maxLoginBlockTimeSetting = objectDao.getObjectByParam(CommonAppSetting.class,
							"settingName", AppConstants.FAILED_LOGIN_LOCK_DURATION_IN_MINUTES);
					if (null != maxLoginBlockTimeSetting && maxLoginBlockTimeSetting.getSettingValue() != null) {
						lockExpirationTime = LocalDateTime.now()
								.plusMinutes(Integer.parseInt(maxLoginBlockTimeSetting.getSettingValue()));
					} else {
						lockExpirationTime = LocalDateTime.now().plusMinutes(AppConstants.TEN);
					}

					existingSystemUserByEmail.setIdLockExpirationTime(
							existingSystemUserByEmail.getIdLockExpirationTime() == null ? lockExpirationTime
									: existingSystemUserByEmail.getIdLockExpirationTime());

					objectDao.updateObject(existingSystemUserByEmail);

					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
					String lockExpirationTimeStr = lockExpirationTime.format(dateFormatter);
					throw new NotFoundException(
							"Your account has been temporarily locked due to multiple failed login attempts. Your account will be unlocked at "
									+ lockExpirationTimeStr + ". Please try again later.");
				}
			}

		}

		// Fetch the selected user role
		SystemUserRole systemUserRoleSelectedFrontEnd = objectDao.getObjectById(SystemUserRole.class,
				authRequest.getSystemUserRoleId());
		if (systemUserRoleSelectedFrontEnd == null) {
			throw new NotFoundException("Please Select Proper Role... Selected Role Not Found...");
		}

		// Check if the password exists in the database
		if (existingSystemUserByEmail.getPassword() == null) {
			throw new NotFoundException("Password Not Present in DB. Please inform the tech team...");
		}
		Boolean isPasswordSame = passwordEncoder.matches(authRequest.getPassword(),
				existingSystemUserByEmail.getPassword());
		if (isPasswordSame
				&& existingSystemUserByEmail.getRole().getSystemUserRoleId() == authRequest.getSystemUserRoleId()) {
			existingSystemUserByEmail.setFailedLoginAttempt(AppConstants.ZERO);
			existingSystemUserByEmail.setIdLockExpirationTime(null);
			response.setResult(existingSystemUserByEmail);
			response.setStatus(ErrorConstants.SUCESS);
			response.setMessage("User Login Successfully...");
			objectDao.updateObject(existingSystemUserByEmail);

		} else {
			existingSystemUserByEmail
					.setFailedLoginAttempt(existingSystemUserByEmail.getFailedLoginAttempt() == null ? AppConstants.ONE
							: (existingSystemUserByEmail.getFailedLoginAttempt() + AppConstants.ONE));

			objectDao.updateObject(existingSystemUserByEmail);

			throw new NotFoundException("Invalid credentials or role.");
		}

		return response;
	}
}
