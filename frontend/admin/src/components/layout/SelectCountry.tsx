import { useState } from "react";
import {
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";
// data
import { availableCountryData } from "data/countryData";

const SelectCountry = () => {
  const [country, setCountry] = useState<string>("한국");

  const changeCountry = (value: string) => {
    setCountry(value);
  };

  return (
    <UncontrolledDropdown setActiveFromChild className={"dropdownWrap"}>
      <DropdownToggle tag="a" className={`dropdownToggle`} caret>
        <i className={"ni ni-world-2"} />
        <span style={{ marginLeft: "5px" }}>{country}</span>
      </DropdownToggle>
      <DropdownMenu className={"dropdownMenu"}>
        {availableCountryData.map((el, idx) => (
          <DropdownItem key={idx} onClick={() => changeCountry(el.country)}>
            {el.country}
          </DropdownItem>
        ))}
      </DropdownMenu>
    </UncontrolledDropdown>
  );
};

export default SelectCountry;
