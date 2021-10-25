CREATE ALIAS TO_NUMBER AS $$
Long toNumber(String value) {
    return value == null ? null : Long.valueOf(value);
}
$$;

CREATE ALIAS SUBSTRB AS $$
String substrb(String value, int start, int end) {
	return value == null ? null : value.substring(start-1, end);
	}
$$;