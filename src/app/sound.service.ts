import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SoundService {

  BASE_SOUND_URL: string = "assets/sounds/";

  isPlaySound: boolean = false;
  SOUND_URLS: any = {
    "LOGIN_SUCESS_SOUND": "LOGIN_SUCESS_SOUND.mp3",
    "LOGIN_FAILURE_SOUND": "LOGIN_FAILURE_SOUND.mp3",
    "CLICK_SOUND": "CLICK_SOUND.mp3"
  }
  constructor() { }

  soundProvider(type: string): string | null {
    if (this.SOUND_URLS[type]) {
      return this.BASE_SOUND_URL + this.SOUND_URLS[type];
    } else {
      return null;
    }
  }

  playSound(type: string): void {
    if (this.isPlaySound) {
      const soundFile = this.soundProvider(type);
      if (soundFile) {
        const audio = new Audio(soundFile);
        audio.play().catch(error => {
          console.error('Error playing sound:', error);
        });
      } else {
        console.warn(`Sound type "${type}" not found.`);
      }
    }
  }
  onOffSound(type: string) {
    if (type == 'on') {
      this.isPlaySound = true;
    } else {
      this.isPlaySound = false;
    }
  }
}
