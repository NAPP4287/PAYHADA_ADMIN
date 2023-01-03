export interface LabelInputProps {
  label: string;
  type: any;
  placeholder?: string;
  setChangeData?: Function | any;
  value: string;
}

export interface LabelSelectProps {
  label: string;
  type: any;
  setChangeData?: Function | any;
  value: string;
  dataArray: Array<string>;
}
