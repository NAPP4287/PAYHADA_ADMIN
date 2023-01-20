import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { dummyNavMenu } from "dummy/dummyData";
import {
  Collapse,
  Navbar,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
} from "reactstrap";
import Logo from "assets/images/payhada_logo_1.png";
// interfaces
import { SubMenuType, NavType } from "interface/interfaceNav";
import { ObjectBracketBooleanType } from "interface/InterfaceCommon";

const NavSide = () => {
  const navigate = useNavigate();
  const [menuDrop, setMenumDrop] = useState<ObjectBracketBooleanType>({
    0: false,
    1: false,
    2: false,
  });
  const [subActive, setSubActive] = useState<string>("");
  const [prevIdx, setPrevIdx] = useState<number>(0);
  const [fixIdx, setFixIdx] = useState<number | null>(null);

  const onToggle = (idx: number) => {
    setMenumDrop({ ...menuDrop, [prevIdx]: false, [idx]: !menuDrop[idx] });
    setPrevIdx(idx);
  };

  const onClickMovePage = (url: string) => {
    navigate(url);
  };

  const handleMenu = (name: string, url: string, mainIdx: number) => {
    onClickMovePage(url);
    setSubActive(name);
    setFixIdx(mainIdx);
  };

  const goMain = () => {
    onClickMovePage("/");
    setSubActive("");
    setMenumDrop({ 0: false, 1: false, 2: false });
    setFixIdx(null);
  };

  const dummyMenu = dummyNavMenu;

  const sortingMenu = (el: any) => {
    return el.sort((a: NavType | SubMenuType, b: NavType | SubMenuType) => {
      if (a.sort > b.sort) {
        return 1;
      }
      if (a.sort < b.sort) {
        return -1;
      }
      return 0;
    });
  };

  return (
    <Navbar
      className="navbar-vertical fixed-left navbar-light bg-white"
      expand="md"
      id="sidenav-main">
      <NavbarBrand className="pt-0" onClick={goMain}>
        <img alt={"payhada logo"} className="navbar-brand-img" src={Logo} />
      </NavbarBrand>
      <Collapse navbar>
        <Nav navbar>
          {sortingMenu(dummyMenu).map((el: NavType, index: number) => (
            <div key={index}>
              <NavItem className="cursor">
                <NavLink
                  className={`textBold ${
                    (menuDrop[index] || fixIdx === index) &&
                    "active menuOpenActive"
                  }`}
                  onClick={() => onToggle(index)}>
                  <i className={el.icon} />
                  {el.name}
                </NavLink>
              </NavItem>

              <Collapse isOpen={menuDrop[index] || fixIdx === index}>
                {sortingMenu(el.sub).map((el: SubMenuType, idx: number) => (
                  <li
                    key={idx}
                    value={el.name}
                    className={`menuSubItem ${
                      el.name === subActive && "fixMenu"
                    }`}
                    onClick={() => handleMenu(el.name, el.url, index)}>
                    {el.name}
                  </li>
                ))}
              </Collapse>
            </div>
          ))}
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default NavSide;
