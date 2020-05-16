import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedStateService {

  private _error: string;

  constructor() { }


  get error(): string {
    return this._error;
  }

  set error(value: string) {
    this._error = value;
  }
}
