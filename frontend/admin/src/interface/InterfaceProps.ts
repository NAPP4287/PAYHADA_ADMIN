import { CSSProperties, ReactElement } from "react";
import { ITranslation } from "./InterfaceCommon";
import { TableListResType } from "./apiType/InterfaceSetting";
import { TABLE_INFO_TYPE } from "./InterfaceConstants";

// LoginMain
export interface LoginMainProps extends ITranslation {
  email: string;
  setEmail: Function;
  password: string;
  setPassword: Function;
  getLogin: Function;
}

// LoginCert
export interface LoginCertProps extends ITranslation {
  email: string;
  getLogin: Function;
}

// LabelInput
export interface LabelInputProps {
  label?: string | null;
  type: any;
  placeholder: string;
  setChangeData: Function;
  value: string;
  isFailed?: boolean;
  failedText?: string | null;
  maxLength?: number;
  border?: string | null;
}

// LabelSelect
export interface LabelSelectProps {
  label?: string | null;
  type: string;
  setChangeData: Function;
  value: string;
  dataArray: Array<SelectArrayType>;
  isFailed?: boolean;
  failedText?: string;
  border?: string | null;
}

export type SelectArrayType = {
  name: string;
  value: string;
};

// Timer
export interface TimerProps {
  minutes: number;
  seconds: number;
  setMinutes: Function;
  setSeconds: Function;
  setIsEnd: Function;
  isEnd: boolean;
}

export interface InputTimerProps extends LabelInputProps {
  seconds: number;
  setSeconds: Function;
  isEnd: boolean;
  setIsEnd: Function;
  resetTime: number;
  actionFunc: Function;
}

// ListFilter
export interface ListFilterProps {
  filterTableList: any;
  action: Function;
  tab?: boolean;
  flexRate?: number;
}

// ListTable
export interface ListTableProps {
  isCheck: boolean;
  list: Array<TableListResType>;
  info: Array<TABLE_INFO_TYPE>;
  action: Function;
}

// RemitList
export interface RemitListProps {
  list: Array<TableListResType>;
}

// FilterRow
export interface FilterRowProps {
  rowList: Array<RowListType>;
}

export type RowListType = {
  title: string;
  content: ReactElement;
  flexRate: number;
};

// RadioButton
export interface RadioButtonProps {
  label: string;
  value: string;
  changeValue: Function;
  activeValue: string;
}

// RadioList
export interface RadioListProps {
  radioList: Array<RadioProps>;
  type: string;
  changeValue: Function;
  activeValue: string;
}
interface RadioProps {
  label: string;
  value: string;
}

// AtomButton
export interface AtomButtonProps {
  title: string;
  size: string;
  buttonStyle?: CSSProperties;
  type: string;
  block?: boolean;
  action(): void;
}
