import {Router} from '@angular/router';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export default class ServerService {

  // TODO: Support backend on https://api.therest
  private host: string;
  private port = '8080';

  constructor() {
    this.host = this.parseHost();
  }

  private parseHost(): string {
    const url = window.location.href;
    const protocolPattern = '://';
    const afterProtocol = url.substr(url.indexOf(protocolPattern) + protocolPattern.length);
    const hostAndPort = afterProtocol.substr(0, afterProtocol.indexOf('/'));
    if (hostAndPort.indexOf(':') < 0) {
      return hostAndPort;
    }
    return hostAndPort.split(':')[0];
  }

  getHttpUrl() {
    return `http://${this.host}:${this.port}`;
  }

  getWsUrl() {
    return `ws://${this.host}:${this.port}`;
  }

  setHost(host: string) {
    this.host = host;
  }
}
