import React, { Component, useState, useEffect } from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import Header from './components/fragment/header';
import Footer from './components/fragment/footer';
import Main from './pages';
import Login from './pages/login';
import { ErrorBoundary } from './ErrorBoundary';
import AuthRoute from './AuthRoute';

const OurFallbackComponent = ({ error, componentStack, resetErrorBoundary }) => {
  return (
      <div>
        <h1>An error occurred: {error.message}</h1>
        <button onClick={resetErrorBoundary}>Try again</button>
      </div>
  );
};

function App() {
  const pathName = useLocation().pathname;

  const [width, setWidth] = useState(window.innerWidth);
  const [isMobile, setIsMobile] = useState(false);

  const updateDimensions = () => {
    setWidth(window.innerWidth);
  };

  useEffect(() => {
    window.addEventListener('resize', updateDimensions);
    return () => window.removeEventListener('resize', updateDimensions);
  }, []);

  useEffect(() => {
    // console.log('width:::',width);
    if (width < 768) setIsMobile(true);
    else setIsMobile(false);
  }, [width]);

  return (
      <div className={'h-100'}>
        <ErrorBoundary FallbackComponent={OurFallbackComponent}>
            {pathName === '/login' ? null : (
                <Header />
            )}
            <Switch>
              <AuthRoute chkType={'login'} exact path="/login" component={Login} />
              {/*<Route exact path="/" render={(props) => <Main {...props} isMobile={isMobile} />} />*/}
              <AuthRoute chkType={'login'} exact path="/" component={Main} />
            </Switch>
            <Footer />
        </ErrorBoundary>
      </div>
  )
}

export default withRouter(App);
