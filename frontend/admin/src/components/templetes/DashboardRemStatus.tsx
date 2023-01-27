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

        <div className={styles.tableWrap}>
          <Table className="align-items-center smarginTop" responsive>
            <thead className="thead-light">
              <tr>
                <th scope="col">remittance</th>
                <th scope="col">all</th>
                <th scope="col">process</th>
                <th scope="col">complete</th>
                <th scope="col">cancel</th>
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
