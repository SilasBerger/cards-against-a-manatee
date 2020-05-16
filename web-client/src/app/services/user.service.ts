import { Injectable } from '@angular/core';
import User from '../model/user';
import ServerService from './server.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _user: User;

  constructor(private _serverService: ServerService) {
  }

  createUser(userName: string): Promise<User> {
    return new Promise((resolve, reject) => {
      if (this._user) {
        resolve(this._user);
      } else {
        if (!userName) {
          reject();
        } else {
          fetch(this._serverService.getHttpUrl() + '/player', { method: 'POST', body: userName })
            .then(res => res.json())
            .then(resJson => {
              const user = new User(resJson.id, resJson.name);
              this._user = user;
              resolve(user);
            });
        }
      }
    });
  }

  get userId() {
    if (!this._user) {
      return null;
    }
    return this._user.id;
  }

  get userName() {
    if (!this._user) {
      return null;
    }
    return this._user.userName;
  }

  get user() {
    return this._user;
  }

  reset() {
    this._user = null;
  }

}
