import { Table, Card, CardBody } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// data
import { remStatusData } from "data/dashBoardData";
// util
import { addComma } from "utils/utilCommon";

const DashboardRemStatus = () => {
  return (
    <Card>
      <CardBody>
        <span className={`boxTitle ${styles.chartTitle}`}>
          Remittance status
        </span>

        <div>
          <Table className="align-items-center smarginTop" responsive>
            <thead className="thead-light">
              <tr>
                <th scope="col">송금현황</th>
                <th scope="col">전체</th>
                <th scope="col">진행중</th>
                <th scope="col">완료</th>
                <th scope="col">취소</th>
              </tr>
            </thead>
            <tbody>
              {remStatusData.map((el, idx) => (
                <tr key={idx}>
                  <th scope="row">
                    <span className="mb-0 text-sm">{el.title}</span>
                  </th>

                  <td>{addComma(el.all)}</td>
                  <td>{addComma(el.process)}</td>
                  <td>{addComma(el.complete)}</td>
                  <td>{addComma(el.cancel)}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </CardBody>
    </Card>
  );
};

export default DashboardRemStatus;
