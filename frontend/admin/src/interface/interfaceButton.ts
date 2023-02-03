export interface RadioButtonProps {
  label: string;
  value: string;
  idx: number;
  changeValue: Function;
}

export interface RadioListProps {
  radioList: Array<RadioProps>;
  type: string;
  changeValue: Function;
}

interface RadioProps {
  label: string;
  value: string;
}
