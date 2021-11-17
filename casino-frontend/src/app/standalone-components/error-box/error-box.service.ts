import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorBoxService {

  errorBoxEmitter: EventEmitter<string>;

  constructor() {
    this.errorBoxEmitter = new EventEmitter<string>();
  }

  showError(message: string) {
    this.errorBoxEmitter.next(message);
  }

  get eventEmitter() {
    return this.errorBoxEmitter;
  }
}
