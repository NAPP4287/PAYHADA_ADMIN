import { Card, CardBody, Table, Badge } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// data
import { exchangeRateData } from "data/dashBoardData";
// interfaces
import { ITranslation } from "interface/InterfaceCommon";

const DashboardExRateInfo = ({ t }: ITranslation) => {
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
                <th scope="col">{t("Dashboard.nation")}</th>
                <th scope="col">{t("Dashboard.mto")}</th>
                <th scope="col">{t("Dashboard.currency")}</th>
                <th scope="col">{t("Dashboard.aplidExRate")}</th>
                <th scope="col">{t("Dashboard.compareExRate")}</th>
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
