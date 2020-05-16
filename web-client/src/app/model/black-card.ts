export default class BlackCard {

  constructor(private _id, private _text: string, private _pick: number, private _playedById: string) {
  }

  get id() {
    return this._id;
  }

  get text(): string {
    return this._text;
  }

  get pick(): number {
    return this._pick;
  }

  get playedById(): string {
    return this._playedById;
  }
}
