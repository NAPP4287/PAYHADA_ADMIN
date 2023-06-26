const FilterRow = (props: any) => {
  const { rowList } = props;

  return (
    <div style={{ display: "flex" }}>
      {rowList.map((el: any, idx: number) => (
        <ul className="tableList" key={idx}>
          <li className="title">{el.title}</li>
          <li className="content">{el.content}</li>
        </ul>
      ))}
    </div>
  );
};

export default FilterRow;
