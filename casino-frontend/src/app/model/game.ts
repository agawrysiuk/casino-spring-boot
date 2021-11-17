export interface SlotsDto {
  balance: number;
  moneyResult: number;
  mainMessage: string;
  results: number[];
}

export interface RouletteResponseDto {
  mainMessage: string;
  resultMessage: string;
  balance: number;
}

export interface RouletteRequestDto {
  choice: RouletteChoice;
  value: string;
}

export enum RouletteChoice {
  GUESS_SINGLE= "GUESS_SINGLE",
  GUESS_RED_OR_BLACK = "GUESS_RED_OR_BLACK",
  GUESS_EVEN_OR_ODD = "GUESS_EVEN_OR_ODD"
}

export interface TwentyOneResponseDto {
  mainMessage: string;
  yourCards: Card[];
  dealersCards: Card[];
  gameFinished: boolean;
  resultMessage: string;
  balance: number;
}

export interface TwentyOneRequestDto {
  code: TwentyOneRequestCode;
}

export enum TwentyOneRequestCode {
  HIT_ME = "HIT_ME",
  HOLD = "HOLD",
  AGAIN = "AGAIN"
}

export interface Card {
  cardColor: any;
  cardStrength: any;
}
