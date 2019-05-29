import { Injectable } from '@angular/core';
import * as OktaSignIn from '@okta/okta-signin-widget';

@Injectable()
export class OktaService {
  widget;

  constructor() {
    this.widget = new OktaSignIn({
      baseUrl: 'https://dev-607529.okta.com',
      clientId: '0oanddbfnsGCvrR4G356',
      authParams: {
        issuer: 'default',
        responseType: ['id_token', 'token'],
        scopes: ['openid', 'email', 'profile']
      }
    });
  }

  getWidget() {
    return this.widget;
  }

  getIdToken() {
    return this.widget.tokenManager.get('idToken');
  }

  getAccessToken() {
    return this.widget.tokenManager.get('accessToken');
  }
}