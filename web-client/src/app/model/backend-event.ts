import BackendEventHeaders from './backend-event-headers';

export default class BackendEvent {

  constructor(private _headers: BackendEventHeaders, private _data: any) {
  }

  get headers() {
    return this._headers;
  }

  get data() {
    return this._data;
  }

}
