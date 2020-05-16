export default class WhiteCard {
  constructor(private _id, private _text: string, private _playedById: string) {
  }

  get id() {
    return this._id;
  }

  get text(): string {
    return this._text;
  }

  get playedById(): string {
    return this._playedById;
  }
}
