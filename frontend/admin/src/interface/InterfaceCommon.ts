// i18n
import { TFunction, i18n } from "i18next";

export interface ObjectBracketStringType {
  [key: string]: string;
}

export interface ObjectBracketBooleanType {
  [key: string]: boolean;
}

export interface ObjectBracketNumberType {
  [key: string]: number;
}

export interface ObjectBracketObjectType {
  [key: string]: object;
}

export interface ObjectBracketElementType {
  [key: string]: HTMLElement | null;
}

export interface ITranslation {
  t: TFunction;
  i18n?: i18n;
}
