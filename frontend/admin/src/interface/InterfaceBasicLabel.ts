export interface LabelInputProps {
  label: string;
  type: any;
  placeholder?: string;
  setChangeData?: Function | any;
  value: string;
  isFailed?: boolean;
  failedText?: string;
  maxLength?: number;
  labelInputStyle?: object;
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

// Timer
export interface TimerProps {
  minutes: number;
  seconds: number;
  setMinutes: Function;
  setSeconds: Function;
  timerStyle?: object;
}

export interface InputTimerProps extends TimerProps, LabelInputProps {}
