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

interface RadioProps {
  label: string;
  value: string;
}
