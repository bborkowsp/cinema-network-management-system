const FormValidatorPatterns = {
  STREET_AND_BUILDING_NUMBER: '^[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż\\d\\s]+ \\d+(\\/\\d+)?$',
  POSTAL_CODE: "^\\d{2}-\\d{3}$"
};

export default FormValidatorPatterns;
