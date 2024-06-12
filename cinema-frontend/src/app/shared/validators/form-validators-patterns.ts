const FormValidatorPatterns = {
  STREET_AND_BUILDING_NUMBER_PATTERN: '^[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż\\d\\s]+ \\d+(\\/\\d+)?$',
  POSTAL_CODE_PATTERN: "^\\d{2}-\\d{3}$",
  TRAILER_URL_PATTERN: "^https:\/\/www\.youtube\.com\/embed\/[A-Za-z0-9_-]+$",
  DIRECTOR_AND_ACTORS_PATTERN: '^[A-Za-z]+\\s[A-Za-z]+(?:,\\s[A-Za-z]+\\s[A-Za-z]+)*$'
};

export default FormValidatorPatterns;
