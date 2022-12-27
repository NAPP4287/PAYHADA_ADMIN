import React from "react";
import { Route, Redirect } from "react-router-dom";

const isLogin = () => {
    return true
}


export default function AuthRoute({ chkType, component: Component, ...rest }) {
    if (chkType === 'login') {
        return (
            <Route
                {...rest}
                render={(props) =>
                    isLogin() ? <Component {...props} /> : <Redirect to="/login" />
                }
            />
        );
    } else if (chkType === 'auth') { // 권한별 접근 제어 (필요시)
        return (
            // <Route
            //     {...rest}
            //     render={(props) =>
            //     }
            // />
            null
        );
    }
}