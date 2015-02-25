package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import repo.pais.RepoPaises;
import dom.pais.Pais;

public class PaisesFixture extends FixtureScript {
	
	
	public PaisesFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	@Override
	protected void execute(ExecutionContext executionContext) {

		if(estaVacio(executionContext))
		{
			create("Afghanistan",executionContext);
			create("Africa Central",executionContext);
			create("Albania",executionContext);
			create("Algeria",executionContext);
			create("Andorra",executionContext);
			create("Angola",executionContext);
			create("Antigua And Barbuda",executionContext);
			create("Argentina",executionContext);
			create("Armenia",executionContext);
			create("Australia",executionContext);
			create("Austria",executionContext);
			create("Azerbaijan",executionContext);
			create("Bahamas",executionContext);
			create("Bahrain",executionContext);
			create("Bangladesh",executionContext);
			create("Barbados",executionContext);
			create("Belarus",executionContext);
			create("Belgium",executionContext);
			create("Benin",executionContext);
			create("Bolivia",executionContext);
			create("Bosnia And Herzegovina",executionContext);
			create("Botswana",executionContext);
			create("Brazil",executionContext);
			create("Brunei",executionContext);
			create("Bulgaria",executionContext);
			create("Burkina Faso",executionContext);
			create("Burundi",executionContext);
			create("Cambodia",executionContext);
			create("Cameroon",executionContext);
			create("Canada",executionContext);
			create("Cape Verde",executionContext);
			create("Chad",executionContext);
			create("Chile",executionContext);
			create("China",executionContext);
			create("Colombia",executionContext);
			create("Comoros",executionContext);
			create("Congo Brazzaville",executionContext);
			create("Costa Rica",executionContext);
			create("Croacia",executionContext);
			create("Cuba",executionContext);
			create("Cyprus",executionContext);
			create("Czech Republic",executionContext);
			create("Denmark",executionContext);
			create("Djibouti",executionContext);
			create("Dominican Republic",executionContext);
			create("Dominica",executionContext);
			create("East Timor",executionContext);
			create("Ecuador",executionContext);
			create("Egypt",executionContext);
			create("El Salvador",executionContext);
			create("England",executionContext);
			create("Equatorial Guinea",executionContext);
			create("Eritrea",executionContext);
			create("España",executionContext);
			create("Estonia",executionContext);
			create("Ethiopia",executionContext);
			create("Europe",executionContext);
			create("Finland",executionContext);
			create("France",executionContext);
			create("Gabon",executionContext);
			create("Gambia",executionContext);
			create("Georgia",executionContext);
			create("Germany",executionContext);
			create("Ghana",executionContext);
			create("Greece",executionContext);
			create("Greenland",executionContext);
			create("Grenada",executionContext);
			create("Guam",executionContext);
			create("Guatemala",executionContext);
			create("Guernsey",executionContext);
			create("Guinea Bissau",executionContext);
			create("Guinea",executionContext);
			create("Guyane",executionContext);
			create("Honduras",executionContext);
			create("Hong Kong",executionContext);
			create("Hungary",executionContext);
			create("Iceland",executionContext);
			create("India",executionContext);
			create("Indonesia",executionContext);
			create("Iran",executionContext);
			create("Iraq",executionContext);
			create("Ireland",executionContext);
			create("Israel",executionContext);
			create("Italy",executionContext);
			create("Ivory Coast",executionContext);
			create("Jamaica",executionContext);
			create("Japan",executionContext);
			create("Jordan",executionContext);
			create("Kazakhstan",executionContext);
			create("Kenya",executionContext);
			create("Kiribati",executionContext);
			create("Kuwait",executionContext);
			create("Kyrgyzstan",executionContext);
			create("Laos",executionContext);
			create("Latvia",executionContext);
			create("Lebanon",executionContext);
			create("Lesoto",executionContext);
			create("Libya (New)",executionContext);
			create("Libya",executionContext);
			create("Liechtenstein",executionContext);
			create("Lithuania",executionContext);
			create("London",executionContext);
			create("Luxembourg",executionContext);
			create("Macau",executionContext);
			create("Macedonia",executionContext);
			create("Madagascar",executionContext);
			create("Malawi",executionContext);
			create("Malaysia",executionContext);
			create("Maldives",executionContext);
			create("Mali",executionContext);
			create("Marshall Islands",executionContext);
			create("Mauritiana",executionContext);
			create("Mauritius",executionContext);
			create("Mexico",executionContext);
			create("Micronesia",executionContext);
			create("Moldova",executionContext);
			create("Mongolia",executionContext);
			create("Montenegro",executionContext);
			create("Morocco",executionContext);
			create("Mozambique",executionContext);
			create("Myanmar",executionContext);
			create("Namibia",executionContext);
			create("Nauru",executionContext);
			create("Nepal",executionContext);
			create("Netherlands",executionContext);
			create("New Zealand",executionContext);
			create("Nicaragua",executionContext);
			create("Nigeria",executionContext);
			create("Niger",executionContext);
			create("North Holland",executionContext);
			create("Norway",executionContext);
			create("Oman",executionContext);
			create("Pakistan",executionContext);
			create("Palau",executionContext);
			create("Palestine",executionContext);
			create("Panama",executionContext);
			create("Papua New Guinea",executionContext);
			create("Paraguay",executionContext);
			create("Peru",executionContext);
			create("Philippines",executionContext);
			create("Poland",executionContext);
			create("Portugal",executionContext);
			create("Puerto Rico",executionContext);
			create("Qatar",executionContext);
			create("Republica Democratica del Congo",executionContext);
			create("Romania",executionContext);
			create("Russia",executionContext);
			create("Rwanda",executionContext);
			create("Sahrawi Arab",executionContext);
			create("Saint Kitts and Nevis",executionContext);
			create("Saint Lucia",executionContext);
			create("Saint Vincent and the Grenadines",executionContext);
			create("Samoa",executionContext);
			create("San Marino",executionContext);
			create("Sao Tome and Principe",executionContext);
			create("Saudi Arabia",executionContext);
			create("Scotland",executionContext);
			create("Senegal",executionContext);
			create("Serbia",executionContext);
			create("Seychelles",executionContext);
			create("Sierra Leone",executionContext);
			create("Singapore",executionContext);
			create("Slovakia",executionContext);
			create("Slovenia",executionContext);
			create("Solomon Islands",executionContext);
			create("Somalia",executionContext);
			create("South Korea",executionContext);
			create("South Sudan",executionContext);
			create("St. Patrick's Flag",executionContext);
			create("Sudan",executionContext);
			create("Sur Africa",executionContext);
			create("Suriname",executionContext);
			create("Sweden",executionContext);
			create("Switzerland",executionContext);
			create("Syria",executionContext);
			create("Taiwan",executionContext);
			create("Tajikistan",executionContext);
			create("Tanzania",executionContext);
			create("Thailand",executionContext);
			create("Togo",executionContext);
			create("Tonga",executionContext);
			create("Trinidad and Tobago",executionContext);
			create("Tunisia",executionContext);
			create("Turkey",executionContext);
			create("Turkmenistan",executionContext);
			create("Uganda",executionContext);
			create("Ukraine",executionContext);
			create("Ulster",executionContext);
			create("United Arab Emirates",executionContext);
			create("United Kingdom",executionContext);
			create("United States",executionContext);
			create("Uruguay",executionContext);
			create("US Virgin Islands",executionContext);
			create("Uzbekistan",executionContext);
			create("Venezuela",executionContext);
			create("Vietnam",executionContext);
			create("Wales",executionContext);
			create("Yemen",executionContext);
			create("Zimbabwe",executionContext);
		}
		
	}
	
	private Pais create(final String nombre,
			ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoPaises.nuevoPais(nombre));
	}
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoPaises.mostrarTodosLosPaises().size()==0);
	}
	@Inject
	private RepoPaises repoPaises;
}
