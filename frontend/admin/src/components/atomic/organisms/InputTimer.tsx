/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect } from "react";
import { InputTimerProps } from "interface/InterfaceBasicLabel";
import { Button, Label } from "reactstrap";
import Timer from "components/atomic/atoms/Timer";

const InputTimer = (props: InputTimerProps) => {
  const {
    label,
    type,
    setChangeData,
    value,
    maxLength,
    placeholder,
    failedText,
    isFailed,
    resetTime,
    seconds,
    setSeconds,
    isEnd,
    setIsEnd,
  } = props;
  const [minutes, setMinutes] = useState<number>(0);
  // const [enterAble, setEnterAble] = useState<boolean>(false);

  useEffect(() => {
    calcTime(seconds);
  }, []);

  const inputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setChangeData(e.target.value);
  };

  const resetTimer = () => {
    calcTime(resetTime);
    setIsEnd(false);
    setChangeData("");
  };

  const calcTime = (seconds: number) => {
    setSeconds(seconds % 60);
    setMinutes(Math.floor(seconds / 60));
  };

  return (
    <div>
      <Label for={type} className="label">
        {label}
      </Label>
      <div className="inputWrap modalHeader">
        <input
          className="inputNone"
          placeholder={placeholder}
          type={type}
          onChange={(e) => inputChange(e)}
          value={value}
          maxLength={maxLength}
        />
        {isEnd ? (
          <Button
            color="secondary"
            type="button"
            className="label"
            onClick={resetTimer}>
            재요청
          </Button>
        ) : (
          <Timer
            seconds={seconds}
            setSeconds={setSeconds}
            minutes={minutes}
            setMinutes={setMinutes}
            setIsEnd={setIsEnd}
            isEnd={isEnd}
          />
        )}
      </div>
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default InputTimer;
