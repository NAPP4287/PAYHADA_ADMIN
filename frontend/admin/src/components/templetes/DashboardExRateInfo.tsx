import { Card, CardBody, Table, Badge } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// data
import { exchangeRateData } from "data/dashBoardData";

const DashboardExRateInfo = () => {
  return (
    <Card>
      <CardBody>
        <span className={`boxTitle ${styles.chartTitle}`}>
          Exchange Rate Info
        </span>

        <div>
          <Table className="align-items-center smarginTop" responsive>
            <thead className="thead-light">
              <tr>
                <th scope="col">국가</th>
                <th scope="col">MTO</th>
                <th scope="col">통화</th>
                <th scope="col">적용환율</th>
                <th scope="col">전율대비</th>
              </tr>
            </thead>
            <tbody>
              {exchangeRateData.map((el, idx) => (
                <tr key={idx} className={styles.tableRow}>
                  <td>{el.nation}</td>
                  <td>{el.mto}</td>
                  <td>
                    <Badge color="green">{el.currency}</Badge>
                  </td>
                  <td>{el.standardAmt}</td>
                  <td>{el.variableAmt}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </CardBody>
    </Card>
  );
};

export default DashboardExRateInfo;
