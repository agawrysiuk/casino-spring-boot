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

export interface EditCasinoUserRequest {
  firstName: string;
  secondName: string;
  birthDate: Date;
  country: string;
}

export interface EditPasswordRequest {
  oldPassword: string;
  password: string;
  matchingPassword: string;
}

export interface CreditCardObject {
  firstName: string;
  surname: string;
  cardNumber: string;
  expiryDate: string;
  ccv: string;
  depositAmount: number;
}
