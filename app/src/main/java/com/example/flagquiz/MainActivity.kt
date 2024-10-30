package com.example.flagquiz
//Heather Watson Intro to mobile programming

import android.os.Bundle
import android.os.HandlerThread
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class Quiz : Thread  //Data model inherits from thread class
{

    //File names must be converted to lowercase
    private var files = arrayOf("Afghanistan.bmp", "Albania.bmp", "Algeria.bmp", "American_Samoa.bmp", "Andorra.bmp", "Angola.bmp", "Anguilla.bmp",
        "Antigua_and_Barbuda.bmp", "Argentina.bmp","Armenia.bmp", "Aruba.bmp","Australia.bmp", "Austria.bmp","Azerbaijan.bmp",
        "Bahamas.bmp", "Bahrain.bmp", "Bangladesh.bmp", "Barbados.bmp", "Belarus.bmp", "Belgium.bmp", "Belize.bmp", "Benin.bmp",
        "Bermuda.bmp", "Bhutan.bmp", "Bolivia.bmp", "Bosnia.bmp", "Botswana.bmp", "Brazil.bmp", "British_Virgin_Islands.bmp",
        "Brunei.bmp", "Bulgaria.bmp", "Burkina_Faso.bmp", "Burundi.bmp", "Cambodia.bmp", "Cameroon.bmp", "Canada.bmp", "Cape_Verde.bmp",
        "Cayman_Islands.bmp", "Central_African_Republic.bmp", "Chad.bmp", "Chile.bmp", "China.bmp","Christmas_Island.bmp",
        "Colombia.bmp","Comoros.bmp","Cook_Islands.bmp","Costa_Rica.bmp","Croatia.bmp","Cuba.bmp","Cyprus.bmp","Cyprus_Northern.bmp",
        "Czech_Republic.bmp","Cte_dIvoire.bmp","Democratic_Republic_of_the_Congo.bmp","Denmark.bmp","Djibouti.bmp","Dominica.bmp",
        "Dominican_Republic.bmp","Ecuador.bmp","Egypt.bmp","El_Salvador.bmp","Equatorial_Guinea.bmp","Eritrea.bmp","Estonia.bmp",
        "Ethiopia.bmp","Falkland_Islands.bmp","Faroe_Islands.bmp","Fiji.bmp","Finland.bmp","France.bmp","French_Polynesia.bmp",
        "Gabon.bmp","Gambia.bmp","Georgia.bmp","Germany.bmp","Ghana.bmp","Gibraltar.bmp","Greece.bmp","Greenland.bmp","Grenada.bmp",
        "Guam.bmp","Guatemala.bmp","Guinea.bmp","Guinea_Bissau.bmp","Guyana.bmp","Haiti.bmp","Honduras.bmp","Hong_Kong.bmp","Hungary.bmp",
        "Iceland.bmp", "India.bmp","Indonesia.bmp","Iran.bmp","Iraq.bmp", "Ireland.bmp","Israel.bmp","Italy.bmp","Jamaica.bmp","Japan.bmp",
        "Jordan.bmp", "Kazakhstan.bmp","Kenya.bmp","Kiribati.bmp","Kuwait.bmp","Kyrgyzstan.bmp","Laos.bmp","Latvia.bmp","Lebanon.bmp",
        "Lesotho.bmp","Liberia.bmp","Libya.bmp","Liechtenstein.bmp","Lithuania.bmp","Luxembourg.bmp","Macao.bmp","Macedonia.bmp",
        "Madagascar.bmp","Malawi.bmp","Malaysia.bmp","Maldives.bmp","Mali.bmp","Malta.bmp","Marshall_Islands.bmp","Martinique.bmp",
        "Mauritania.bmp","Mauritius.bmp","Mexico.bmp","Micronesia.bmp","Moldova.bmp","Monaco.bmp","Mongolia.bmp","Montserrat.bmp",
        "Morocco.bmp","Mozambique.bmp","Myanmar.bmp","Namibia.bmp","Nauru.bmp","Nepal.bmp","Netherlands.bmp","Netherlands_Antilles.bmp",
        "New_Zealand.bmp", "Nicaragua.bmp","Niger.bmp","Nigeria.bmp","Niue.bmp","Norfolk_Island.bmp","North_Korea.bmp","Norway.bmp",
        "Oman.bmp","Pakistan.bmp","Palau.bmp","Panama.bmp","Papua_New_Guinea.bmp","Paraguay.bmp","Peru.bmp","Philippines.bmp",
        "Pitcairn_Islands.bmp","Poland.bmp","Portugal.bmp","Puerto_Rico.bmp","Qatar.bmp","Republic_of_the_Congo.bmp","Romania.bmp",
        "Russian_Federation.bmp","Rwanda.bmp","Saint_Kitts_and_Nevis.bmp","Saint_Lucia.bmp","Saint_Pierre.bmp","Saint_Vicent_and_the_Grenadines.bmp",
        "Samoa.bmp","San_Marino.bmp","Sao_Tom_and_Prncipe.bmp","Saudi_Arabia.bmp","Senegal.bmp","Serbia_and_Montenegro.bmp",
        "Seychelles.bmp", "Sierra_Leone.bmp", "Singapore.bmp","Slovakia.bmp","Slovenia.bmp","Soloman_Islands.bmp","Somalia.bmp",
        "South_Africa.bmp","South_Georgia.bmp","South_Korea.bmp","Soviet_Union.bmp","Spain.bmp","Sri_Lanka.bmp","Sudan.bmp","Suriname.bmp",
        "Swaziland.bmp","Sweden.bmp","Switzerland.bmp","Syria.bmp", "Taiwan.bmp","Tajikistan.bmp","Tanzania.bmp","Thailand.bmp",
        "Tibet.bmp", "Timor_Leste.bmp", "Togo.bmp","Tonga.bmp","Trinidad_and_Tobago.bmp","Tunisia.bmp", "Turkey.bmp","Turkmenistan.bmp",
        "Turks_and_Caicos_Islands.bmp", "Tuvalu.bmp","UAE.bmp","Uganda.bmp","Ukraine.bmp","United_Kingdom.bmp","United_States_of_America.bmp",
        "Uruguay.bmp","US_Virgin_Islands.bmp","Uzbekistan.bmp","Vanuatu.bmp","Vatican_City.bmp","Venezuela.bmp","Vietnam.bmp",
        "Wallis_and_Futuna.bmp","Yemen.bmp","Zambia.bmp","Zimbabwe.bmp")
    private var countries = arrayOf("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
        "Antigua and Barbuda", "Argentina","Armenia", "Aruba","Australia", "Austria","Azerbaijan",
        "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
        "Bermuda", "Bhutan", "Bolivia", "Bosnia", "Botswana", "Brazil", "British Virgin Islands",
        "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
        "Cayman Islands", "Central African Republic", "Chad", "Chile", "China","Christmas Island",
        "Colombia","Comoros","Cook Islands","Costa Rica","Croatia","Cuba","Cyprus","Cyprus Northern",
        "Czech Republic","Cte dIvoire","Democratic Republic of the Congo","Denmark","Djibouti","Dominica",
        "Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia",
        "Ethiopia","Falkland Islands","Faroe Islands","Fiji","Finland","France","French Polynesia",
        "Gabon","Gambia","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada",
        "Guam","Guatemala","Guinea","Guinea Bissau","Guyana","Haiti","Honduras","Hong Kong","Hungary",
        "Iceland", "India","Indonesia","Iran","Iraq", "Ireland","Israel","Italy","Jamaica","Japan",
        "Jordan", "Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon",
        "Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macao","Macedonia",
        "Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique",
        "Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montserrat",
        "Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal","Netherlands","Netherlands Antilles",
        "New Zealand", "Nicaragua","Niger","Nigeria","Niue","Norfolk Island","North Korea","Norway",
        "Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines",
        "Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Republic of the Congo","Romania",
        "Russian Federation","Rwanda","Saint Kitts and Nevis","Saint Lucia","Saint Pierre","Saint Vicent and the Grenadines",
        "Samoa","San Marino","Sao Tom and Prncipe","Saudi Arabia","Senegal","Serbia and Montenegro",
        "Seychelles", "Sierra Leone", "Singapore","Slovakia","Slovenia","Soloman Islands","Somalia",
        "South Africa","South Georgia","South Korea","Soviet Union","Spain","Sri Lanka","Sudan","Suriname",
        "Swaziland","Sweden","Switzerland","Syria", "Taiwan","Tajikistan","Tanzania","Thailand",
        "Tibet", "Timor-Leste", "Togo","Tonga","Trinidad and Tobago","Tunisia", "Turkey","Turkmenistan",
        "Turks and Caicos Islands", "Tuvalu","UAE","Uganda","Ukraine","United Kingdom","United States of America",
        "Uruguay","US Virgin Islands","Uzbekistan","Vanuatu","Vatican City","Venezuela","Vietnam",
        "Wallis and Futuna","Yemen","Zambia","Zimbabwe")


    private var noFlags: Int = 0//Total number of flags to display
    private var duration: Int = 0//The number of seconds App displays each screen
    private var count: Int = 0





    constructor(noFlags: Int, duration: Int) {

        this.noFlags = noFlags
        this.duration = duration





    }


    //This method continuously selects the next image to be displayed along with whatever
    // text information is needed (Either “What Flag Is This?” or the actual country's name). The method runs indefinitely.
    override public fun run()
    {

        val randomValues = List(noFlags) { Random.nextInt(0, 223) }


        while(true)
        {
            //Needs to call the corresponding random values....
            // find some way to start from 0 and reset on the 10th value

            if (count == 9)
            {
                count = 0
            }

            var handler = QuizHandler(files[randomValues[count]].lowercase(),"What Flag Is This?")
            MainActivity.getInstance().runOnUiThread(handler)
            Thread.sleep(duration.toLong()*1000) //Delay
            handler = QuizHandler(files[randomValues[count]].lowercase(), countries[randomValues[count]])
            MainActivity.getInstance().runOnUiThread(handler)
            Thread.sleep(duration.toLong()*1000) //Delay
            count++
        }



    }

}

class QuizHandler : Runnable
{
    private var fn : String = "" //File name of the image to be displayed by the ImageView
    private var text : String = "" //Text to be displayed in textView
    constructor(fn : String, caption : String)
    {
        this.fn = fn.lowercase()
        this.text = caption
    }
    override fun run()
    {
        var imageView = MainActivity.getInstance().findViewById<ImageView>(R.id.imageView)
        var textView = MainActivity.getInstance().findViewById<TextView>(R.id.textView)
        textView.setText(text)

        var id = MainActivity.getInstance().resources.getIdentifier(fn.lowercase().removeSuffix(".bmp"), "drawable", 	 		                                  MainActivity.getInstance().packageName)
        imageView.setImageResource(id)

    }
}


class MainActivity : AppCompatActivity() {

    companion object
    {
        private var instance : MainActivity? = null
        public fun getInstance() : MainActivity
        {
            return instance!!
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var flagQuiz = Quiz(10,5)
        flagQuiz.start()




        }

}