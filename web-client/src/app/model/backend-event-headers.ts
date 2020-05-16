export default class BackendEventHeaders {

  constructor(private _eventName: string, private _iat: number) {
  }

  get eventName() {
    return this._eventName;
  }

  get iat(){
    return this._iat;
  }

}

