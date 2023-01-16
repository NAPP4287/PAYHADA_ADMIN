export interface LabelInputProps {
  label: string;
  type: any;
  placeholder?: string;
  setChangeData?: Function | any;
  value: string;
  isFailed?: boolean;
  failedText?: string;
  maxLength?: number;
  onEnter: Function;
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
  setIsEnd: Function;
  isEnd: boolean;
}

// export interface

export interface InputTimerProps extends LabelInputProps {
  seconds: number;
  setSeconds: Function;
  isEnd: boolean;
  setIsEnd: Function;
  resetTime: number;
  onEnter: Function;
}
