import { ObjectBracketStringType } from "interface/InterfaceCommon";

const Title = (props: ObjectBracketStringType) => {
  const { title } = props;

  return <h4 className="secondary marginBottom">{title}</h4>;
};

export default Title;
