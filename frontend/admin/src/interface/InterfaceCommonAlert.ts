export interface BasicAlertType {
  isOpen: boolean;
  AlertType: string;
  title: string;
  content: string;
  action: Function | undefined;
  cancel: Function | undefined;
  buttonText: ButtonTextType;
}

type ButtonTextType = {
  confirm: string;
  cancel: string;
};
