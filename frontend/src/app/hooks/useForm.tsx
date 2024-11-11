import { useState } from "react";

/**Custom hook for managing form state.
@param initialState -> This is an object that holds the inital states for form values.
@returns an object contianing the form values, handleChange function, and resetform function

*/

type FormState = Record<string, string>;
export default function useForm(initialState: FormState) {
  const [values, setValues] = useState<FormState>(initialState);

  //Updates and rerenders the input when there is an update
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setValues({
      ...values,
      [name]: value,
    });
  };

  //Resets the form inptus into their inital states
  const resetForm = () => {
    setValues(initialState);
  };
  return {
    values,
    handleChange,
    resetForm,
  };
}
