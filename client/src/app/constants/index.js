export const API_BASE_URL = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com';
export const ACCESS_TOKEN = 'accessToken';

export const OAUTH2_REDIRECT_URI = 'http://test234324.s3-website.us-east-2.amazonaws.com/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const CURRENT_USER_URL = API_BASE_URL + '/accounts/me';

export const SIGNIN_URL = API_BASE_URL + '/authentication/signin';
export const SIGNUP_URL = API_BASE_URL + '/authentication/signup';
export const LOGOUT_URL = API_BASE_URL + '/authentication/logout';

export const FORGOT_PASSWORD_URL = API_BASE_URL + '/authentication/forgot-password';
export const RESET_PASSWORD_URL = API_BASE_URL + '/authentication/reset-password';
