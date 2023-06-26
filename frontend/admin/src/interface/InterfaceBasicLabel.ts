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
  // onEnter: Function;
}

export interface LabelSelectProps {
  label?: string | null;
  type: any;
  setChangeData: Function;
  value: string;
  dataArray: Array<string>;
  isFailed?: boolean;
  failedText?: string;
  border?: string | null;
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
  actionFunc: Function;
}
