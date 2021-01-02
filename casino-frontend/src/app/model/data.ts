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

export interface CasinoUserDto {
  nickname: string;
  balance: number;
  firstName: string;
  secondName: string;
  birthDate: Date;
  country: string;
}

export interface PasswordDto {
  username: string;
  oldPassword: string;
  password: string;
  matchingPassword: string;
}
