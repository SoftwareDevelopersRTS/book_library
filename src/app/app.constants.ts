// src/app/constants.ts

export const ERROR_MESSAGES = {
    FETCH_ROLES: 'Error fetching security roles.',
    INVALID_INPUT: 'Invalid input provided.',
    NOT_FOUND: 'Requested resource not found.',
    UNAUTHORIZED: 'You are not authorized to access this resource.',
    // Add more error messages as needed
};

export const STATUS_CODES = {
    SUCCESS: 200,
    CREATED: 201,
    NO_CONTENT: 204,
    BAD_REQUEST: 400,
    UNAUTHORIZED: 401,
    FORBIDDEN: 403,
    NOT_FOUND: 404,
    INTERNAL_SERVER_ERROR: 500,
    // Add more status codes as needed
};
