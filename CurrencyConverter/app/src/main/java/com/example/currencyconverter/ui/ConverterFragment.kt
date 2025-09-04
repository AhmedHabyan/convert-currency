package com.example.currencyconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentConverterBinding


class ConverterFragment : Fragment() {

    private var adapterItem:ArrayAdapter<String>?=null
    private var _binding:FragmentConverterBinding? =null
    val binding:FragmentConverterBinding get() = _binding!!


    val currencies = listOf(
        "United Arab Emirates Dirham",
        "Afghan Afghani",
        "Albanian Lek",
        "Armenian Dram",
        "Netherlands Antillean Guilder",
        "Angolan Kwanza",
        "Argentine Peso",
        "Australian Dollar",
        "Aruban Florin",
        "Azerbaijani Manat",
        "Bosnia-Herzegovina Convertible Mark",
        "Barbadian Dollar",
        "Bangladeshi Taka",
        "Bulgarian Lev",
        "Bahraini Dinar",
        "Burundian Franc",
        "Bermudan Dollar",
        "Brunei Dollar",
        "Bolivian Boliviano",
        "Brazilian Real",
        "Bahamian Dollar",
        "Bitcoin",
        "Bhutanese Ngultrum",
        "Botswanan Pula",
        "New Belarusian Ruble",
        "Belarusian Ruble",
        "Belize Dollar",
        "Canadian Dollar",
        "Congolese Franc",
        "Swiss Franc",
        "Chilean Unit of Account (UF)",
        "Chilean Peso",
        "Chinese Yuan",
        "Chinese Yuan Offshore",
        "Colombian Peso",
        "Costa Rican Colón",
        "Cuban Convertible Peso",
        "Cuban Peso",
        "Cape Verdean Escudo",
        "Czech Republic Koruna",
        "Djiboutian Franc",
        "Danish Krone",
        "Dominican Peso",
        "Algerian Dinar",
        "Egyptian Pound",
        "Eritrean Nakfa",
        "Ethiopian Birr",
        "Euro",
        "Fijian Dollar",
        "Falkland Islands Pound",
        "British Pound Sterling",
        "Georgian Lari",
        "Guernsey Pound",
        "Ghanaian Cedi",
        "Gibraltar Pound",
        "Gambian Dalasi",
        "Guinean Franc",
        "Guatemalan Quetzal",
        "Guyanaese Dollar",
        "Hong Kong Dollar",
        "Honduran Lempira",
        "Croatian Kuna",
        "Haitian Gourde",
        "Hungarian Forint",
        "Indonesian Rupiah",
        "Israeli New Sheqel",
        "Manx pound",
        "Indian Rupee",
        "Iraqi Dinar",
        "Iranian Rial",
        "Icelandic Króna",
        "Jersey Pound",
        "Jamaican Dollar",
        "Jordanian Dinar",
        "Japanese Yen",
        "Kenyan Shilling",
        "Kyrgystani Som",
        "Cambodian Riel",
        "Comorian Franc",
        "North Korean Won",
        "South Korean Won",
        "Kuwaiti Dinar",
        "Cayman Islands Dollar",
        "Kazakhstani Tenge",
        "Laotian Kip",
        "Lebanese Pound",
        "Sri Lankan Rupee",
        "Liberian Dollar",
        "Lesotho Loti",
        "Lithuanian Litas",
        "Latvian Lats",
        "Libyan Dinar",
        "Moroccan Dirham",
        "Moldovan Leu",
        "Malagasy Ariary",
        "Macedonian Denar",
        "Myanma Kyat",
        "Mongolian Tugrik",
        "Macanese Pataca",
        "Mauritanian Ouguiya",
        "Mauritian Rupee",
        "Maldivian Rufiyaa",
        "Malawian Kwacha",
        "Mexican Peso",
        "Malaysian Ringgit",
        "Mozambican Metical",
        "Namibian Dollar",
        "Nigerian Naira",
        "Nicaraguan Córdoba",
        "Norwegian Krone",
        "Nepalese Rupee",
        "New Zealand Dollar",
        "Omani Rial",
        "Panamanian Balboa",
        "Peruvian Nuevo Sol",
        "Papua New Guinean Kina",
        "Philippine Peso",
        "Pakistani Rupee",
        "Polish Zloty",
        "Paraguayan Guarani",
        "Qatari Rial",
        "Romanian Leu",
        "Serbian Dinar",
        "Russian Ruble",
        "Rwandan Franc",
        "Saudi Riyal",
        "Solomon Islands Dollar",
        "Seychellois Rupee",
        "South Sudanese Pound",
        "Swedish Krona",
        "Singapore Dollar",
        "Saint Helena Pound",
        "Sierra Leonean Leone",
        "Sierra Leonean Leone",
        "Somali Shilling",
        "Surinamese Dollar",
        "São Tomé and Príncipe Dobra",
        "São Tomé and Príncipe Dobra",
        "Salvadoran Colón",
        "Syrian Pound",
        "Swazi Lilangeni",
        "Thai Baht",
        "Tajikistani Somoni",
        "Turkmenistani Manat",
        "Tunisian Dinar",
        "Tongan Paʻanga",
        "Turkish Lira",
        "Trinidad and Tobago Dollar",
        "New Taiwan Dollar",
        "Tanzanian Shilling",
        "Ukrainian Hryvnia",
        "Ugandan Shilling",
        "United States Dollar",
        "Uruguayan Peso",
        "Uzbekistan Som",
        "Sovereign Bolivar",
        "Vietnamese Dong",
        "Vanuatu Vatu",
        "Samoan Tala",
        "CFA Franc BEAC",
        "Silver (troy ounce)",
        "Gold (troy ounce)",
        "East Caribbean Dollar",
        "Caribbean Guilder",
        "Special Drawing Rights",
        "CFA Franc BCEAO",
        "CFP Franc",
        "Yemeni Rial",
        "South African Rand",
        "Zambian Kwacha (pre-2013)",
        "Zambian Kwacha",
        "Zimbabwean Dollar"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDropDown()

        onSwapClicked()


    }

    private fun initDropDown() {



        adapterItem = ArrayAdapter<String>(requireContext(),R.layout.list_item)
        adapterItem?.addAll(currencies)
        binding.autoCompleteFrom.setAdapter(adapterItem)
        binding.autoCompleteTo.setAdapter(adapterItem)
    }

    private fun onSwapClicked(){
        binding.btnSwap.setOnClickListener{
            val fromString= binding.autoCompleteFrom.text.toString()


            binding.autoCompleteFrom.setText(binding.autoCompleteTo.text.toString(),false)
            binding.autoCompleteTo.setText(fromString,false)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }


}