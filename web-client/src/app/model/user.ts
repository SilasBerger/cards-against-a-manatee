export default class User {

  private _id: string;
  private _userName: string;

  constructor(id: string, userName: string) {
    this._id = id;
    this._userName =  userName;
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get userName(): string {
    return this._userName;
  }

  set userName(value: string) {
    this._userName = value;
  }
}
