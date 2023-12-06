import { FilterRowProps, RowListType } from "interface/InterfaceProps";

const FilterRow = (props: FilterRowProps) => {
  const { rowList } = props;

  return (
    <div style={{ display: "flex" }}>
      {rowList.map((el: RowListType, idx: number) => (
        <ul className="tableList" key={idx} style={{ flex: el.flexRate }}>
          <li className="title">{el.title}</li>
          <li className="content">{el.content}</li>
        </ul>
      ))}
    </div>
  );
};

export default FilterRow;
