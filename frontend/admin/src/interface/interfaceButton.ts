import { CSSProperties } from "react";

export interface RadioButtonProps {
  label: string;
  value: string;
  changeValue: Function;
  activeValue: string;
}

export interface RadioListProps {
  radioList: Array<RadioProps>;
  type: string;
  changeValue: Function;
  activeValue: string;
}

export interface AtomButtonProps {
  title: string;
  size: string;
  buttonStyle?: CSSProperties;
  type: string;
  block?: boolean;
  action(): void;
}

interface RadioProps {
  label: string;
  value: string;
}
