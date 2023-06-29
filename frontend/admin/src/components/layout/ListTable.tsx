import { useState } from "react";
import { Badge, Card, CardBody, Table } from "reactstrap";
// component
import PaginationBtn from "components/atomic/atoms/PaginationBtn";
import AtomButton from "components/atomic/atoms/AtomButton";
import LabelSelect from "components/atomic/atoms/LabelSelect";
// data
import { SELECT_ROW_FILTER } from "data/constantsData";
// interface
import { TABLE_INFO_TYPE } from "interface/InterfaceConstants";

const ListTable = (props: any) => {
  const { isCheck, list, info, action } = props;

  const [selectList, setSelectList] = useState<string>("10");

  console.log("INFO", list);

  return (
    <Card>
      <CardBody>
        <div className="alignRow">
          <LabelSelect
            type={"select"}
            setChangeData={setSelectList}
            value={selectList}
            dataArray={SELECT_ROW_FILTER}
          />
          <div>
            <AtomButton
              type="primary"
              size="md"
              title={"등록"}
              action={action}
            />
          </div>
        </div>
        <Table className="align-items-center smarginTop" responsive>
          <thead className="thead-light">
            <tr>
              {isCheck && (
                <th scope="col">
                  <input type="checkbox" />
                </th>
              )}
              {info.map((el: any, idx: number) => (
                <th key={idx} scope="col">
                  {el.title}
                </th>
              ))}
            </tr>
          </thead>

          <tbody>
            {list.map((el: any, idx: number) => (
              <tr key={idx}>
                {isCheck && (
                  <td>
                    <input type="checkbox" />
                  </td>
                )}
                {info.map((el2: TABLE_INFO_TYPE, idx2: number) => (
                  <td key={`td_${idx2}`}>
                    {el2.type === "status" ? (
                      <Badge color="primary">{el[el2["name"]]}</Badge>
                    ) : (
                      el[el2["name"]]
                    )}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </Table>
        <PaginationBtn />
      </CardBody>
    </Card>
  );
};

export default ListTable;
