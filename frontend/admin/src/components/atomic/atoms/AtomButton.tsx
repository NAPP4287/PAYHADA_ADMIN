import { Button } from "reactstrap";
// interface
import { AtomButtonProps } from "interface/interfaceButton";

const AtomButton = (props: AtomButtonProps) => {
  const { title, size, buttonStyle, type, block, action } = props;

  return (
    <Button
      block={block}
      style={
        size === "md"
          ? { ...buttonStyle, paddingLeft: "30px", paddingRight: "30px" }
          : { ...buttonStyle }
      }
      size={size}
      color={type}
      type="button"
      onClick={action}>
      {title}
    </Button>
  );
};

export default AtomButton;
