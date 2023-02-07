import { Table, Card, CardBody } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// data
import { remStatusData } from "data/dashBoardData";
// util
import { addComma } from "utils/utilCommon";
// interfaces
import { ITranslation } from "interface/InterfaceCommon";

const DashboardRemStatus = ({ t }: ITranslation) => {
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
                <th scope="col">{t("Common.remStatus")}</th>
                <th scope="col">{t("Common.all")}</th>
                <th scope="col">{t("Common.onGoing")}</th>
                <th scope="col">{t("Common.complete")}</th>
                <th scope="col">{t("Common.cancel")}</th>
              </tr>
            </thead>
            <tbody>
              {remStatusData.map((el, idx) => (
                <tr key={idx}>
                  <th scope="row">
                    <span className="mb-0 text-sm">
                      {t(`Dashboard.${el.title}`)}
                    </span>
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
