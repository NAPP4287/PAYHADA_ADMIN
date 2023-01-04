export interface LabelInputProps {
  label: string;
  type: any;
  placeholder?: string;
  setChangeData?: Function | any;
  value: string;
  isFailed?: boolean;
  failedText?: string;
}

export interface LabelSelectProps {
  label: string;
  type: any;
  setChangeData?: Function | any;
  value: string;
  dataArray: Array<string>;
  isFailed?: boolean;
  failedText?: string;
}
