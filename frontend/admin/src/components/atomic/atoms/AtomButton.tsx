import { Button } from "reactstrap";

const AtomButton = (props: any) => {
  const { title, size, buttonStyle, type, block, action } = props;

  return (
    <Button
      block={block}
      style={buttonStyle}
      size={size}
      color={type}
      type="button"
      onClick={action}>
      {title}
    </Button>
  );
};

export default AtomButton;
