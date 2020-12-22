export interface UserDto {
  username: string;
  password: string;
  matchingPassword: string;
  email: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}
