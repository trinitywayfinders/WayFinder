# Init
echo "__      __                 _____.__            .___                   "
echo "/  \    /  \_____  ___.__._/ ____\__| ____    __| _/___________  ______"
echo "\   \/\/   /\__  \<   |  |\   __\|  |/    \  / __ |/ __ \_  __ \/  ___/"
echo " \        /  / __ \\___  | |  |  |  |   |  \/ /_/ \  ___/|  | \/\___ \ "
echo "  \__/\  /  (____  / ____| |__|  |__|___|  /\____ |\___  >__|  /____  >"
echo "       \/        \/\/                    \/      \/    \/           \/ "

echo ""
echo ""

echo "__________      .__.__       .___ ___________            "
echo "\______   \__ __|__|  |    __| _/ \_   _____/ _______  __"
echo " |    |  _/  |  \  |  |   / __ |   |    __)_ /    \  \/ /"
echo " |    |   \  |  /  |  |__/ /_/ |   |        \   |  \   / "
echo " |______  /____/|__|____/\____ |  /_______  /___|  /\_/  "
echo "        \/                    \/          \/     \/      "

# Build Auth Server

echo "      ___           ___                       ___     "
echo "     /  /\         /__/\          ___        /__/\    "
echo "    /  /::\        \  \:\        /  /\       \  \:\   "
echo "   /  /:/\:\        \  \:\      /  /:/        \__\:\  "
echo "  /  /:/~/::\   ___  \  \:\    /  /:/     ___ /  /::\ "
echo " /__/:/ /:/\:\ /__/\  \__\:\  /  /::\    /__/\  /:/\:\\"
echo " \  \:\/:/__\/ \  \:\ /  /:/ /__/:/\:\   \  \:\/:/__\/"
echo "  \  \::/       \  \:\  /:/  \__\/  \:\   \  \::/     "
echo "   \  \:\        \  \:\/:/        \  \:\   \  \:\     "
echo "    \  \:\        \  \::/          \__\/    \  \:\    "
echo "     \__\/         \__\/                     \__\/    "

echo "*** Building Auth Server - Begin ***"
cd Code/AuthenticationServer/
./gradlew buildDocker -x test
echo "*** Building Auth Server - Completed ***"


# Return to Root
cd ../../


echo "      ___                         ___           ___               "
echo "     /__/\                       /  /\         /  /\      ___     "
echo "     \  \:\                     /  /::\       /  /::\    /  /\    "
echo "      \__\:\    ___     ___    /  /:/\:\     /  /:/\:\  /  /:/    "
echo "  ___ /  /::\  /__/\   /  /\  /  /:/~/::\   /  /:/~/:/ /__/::\    "
echo " /__/\  /:/\:\ \  \:\ /  /:/ /__/:/ /:/\:\ /__/:/ /:/  \__\/\:\__ "
echo " \  \:\/:/__\/  \  \:\  /:/  \  \:\/:/__\/ \  \:\/:/      \  \:\/\\"
echo "  \  \::/        \  \:\/:/    \  \::/       \  \::/        \__\::/"
echo "   \  \:\         \  \::/      \  \:\        \  \:\        /__/:/ "
echo "    \  \:\         \__\/        \  \:\        \  \:\       \__\/  "
echo "     \__\/                       \__\/         \__\/              "

# Build HighLevel API
echo "*** Building HighLevelAPI - Begin ***"
cd Code/HighLevelAPI/
./gradlew buildDocker -x test
echo "*** Building HighLevelAPI - Completed ***"

# Return to Root
cd ../../


echo "      ___           ___           ___                                   ___           ___     "
echo "     /  /\         /  /\         /__/\          ___       ___          /__/\         /  /\    "
echo "    /  /::\       /  /::\        \  \:\        /  /\     /  /\         \  \:\       /  /:/_   "
echo "   /  /:/\:\     /  /:/\:\        \  \:\      /  /:/    /  /:/          \  \:\     /  /:/ /\  "
echo "  /  /:/~/:/    /  /:/  \:\   ___  \  \:\    /  /:/    /__/::\      _____\__\:\   /  /:/_/::\ "
echo " /__/:/ /:/___ /__/:/ \__\:\ /__/\  \__\:\  /  /::\    \__\/\:\__  /__/::::::::\ /__/:/__\/\:\\"
echo " \  \:\/:::::/ \  \:\ /  /:/ \  \:\ /  /:/ /__/:/\:\      \  \:\/\ \  \:\~~\~~\/ \  \:\ /~~/:/"
echo "  \  \::/~~~~   \  \:\  /:/   \  \:\  /:/  \__\/  \:\      \__\::/  \  \:\  ~~~   \  \:\  /:/ "
echo "   \  \:\        \  \:\/:/     \  \:\/:/        \  \:\     /__/:/    \  \:\        \  \:\/:/  "
echo "    \  \:\        \  \::/       \  \::/          \__\/     \__\/      \  \:\        \  \::/   "
echo "     \__\/         \__\/         \__\/                                 \__\/         \__\/    "
# Build Route API
echo "*** Building Route Api - Begin ***"
cd Code/RouteAPI/
./gradlew buildDocker -x test
echo "*** Building Route Api - Completed ***"

# Return to Root
cd ../../


echo "      ___           ___           ___           ___           ___         ___           ___           ___         ___     "
echo "     /__/\         /  /\         /  /\         /  /\         /  /\       /  /\         /  /\         /  /\       /  /\    "
echo "     \  \:\       /  /:/_       /  /:/_       /  /::\       /  /::\     /  /::\       /  /:/_       /  /:/_     /  /:/_   "
echo "      \  \:\     /  /:/ /\     /  /:/ /\     /  /:/\:\     /  /:/\:\   /  /:/\:\     /  /:/ /\     /  /:/ /\   /  /:/ /\  "
echo "  ___  \  \:\   /  /:/ /::\   /  /:/ /:/_   /  /:/~/:/    /  /:/~/:/  /  /:/~/:/    /  /:/ /:/_   /  /:/ /:/  /  /:/ /::\ "
echo " /__/\  \__\:\ /__/:/ /:/\:\ /__/:/ /:/ /\ /__/:/ /:/___ /__/:/ /:/  /__/:/ /:/___ /__/:/ /:/ /\ /__/:/ /:/  /__/:/ /:/\:\\"
echo " \  \:\ /  /:/ \  \:\/:/~/:/ \  \:\/:/ /:/ \  \:\/:::::/ \  \:\/:/   \  \:\/:::::/ \  \:\/:/ /:/ \  \:\/:/   \  \:\/:/~/:/"
echo "  \  \:\  /:/   \  \::/ /:/   \  \::/ /:/   \  \::/~~~~   \  \::/     \  \::/~~~~   \  \::/ /:/   \  \::/     \  \::/ /:/ "
echo "   \  \:\/:/     \__\/ /:/     \  \:\/:/     \  \:\        \  \:\      \  \:\        \  \:\/:/     \  \:\      \__\/ /:/  "
echo "    \  \::/        /__/:/       \  \::/       \  \:\        \  \:\      \  \:\        \  \::/       \  \:\       /__/:/   "
echo "     \__\/         \__\/         \__\/         \__\/         \__\/       \__\/         \__\/         \__\/       \__\/    "

# Build UserPrefs
echo "*** Building UserPrefs - Begin ***"
cd Code/UserPrefs/
./gradlew buildDocker -x test
echo "*** Building UserPrefs - Completed ***"

# Return to Root
cd ../../

echo "      ___           ___                                                 ___     "
echo "     /  /\         /__/\          ___          ___        ___          /  /\    "
echo "    /  /:/_        \  \:\        /__/\        /__/\      /  /\        /  /::\   "
echo "   /  /:/ /\        \  \:\       \  \:\       \  \:\    /  /:/       /  /:/\:\  "
echo "  /  /:/ /:/_   _____\__\:\       \  \:\       \  \:\  /__/::\      /  /:/~/:/  "
echo " /__/:/ /:/ /\ /__/::::::::\  ___  \__\:\  ___  \__\:\ \__\/\:\__  /__/:/ /:/___"
echo " \  \:\/:/ /:/ \  \:\~~\~~\/ /__/\ |  |:| /__/\ |  |:|    \  \:\/\ \  \:\/:::::/"
echo "  \  \::/ /:/   \  \:\  ~~~  \  \:\|  |:| \  \:\|  |:|     \__\::/  \  \::/~~~~ "
echo "   \  \:\/:/     \  \:\       \  \:\__|:|  \  \:\__|:|     /__/:/    \  \:\     "
echo "    \  \::/       \  \:\       \__\::::/    \__\::::/      \__\/      \  \:\    "
echo "     \__\/         \__\/           ~~~~         ~~~~                   \__\/    "

# Build EnvironmentMetricsService
echo "*** Building EnvironmentMetricsService - Begin ***"
cd Code/EnvironmentMetricsService/
./gradlew buildDocker -x test
echo "*** Building EnvironmentMetricsService - Completed ***"

# Return to Root
cd ../../


echo "      ___                       ___           ___                         ___                                   ___           ___     "
echo "     /  /\        ___          /__/\         /__/\                       /  /\          ___       ___          /  /\         /__/\    "
echo "    /  /:/_      /  /\        |  |::\        \  \:\                     /  /::\        /  /\     /  /\        /  /::\        \  \:\   "
echo "   /  /:/ /\    /  /:/        |  |:|:\        \  \:\    ___     ___    /  /:/\:\      /  /:/    /  /:/       /  /:/\:\        \  \:\  "
echo "  /  /:/ /::\  /__/::\      __|__|:|\:\   ___  \  \:\  /__/\   /  /\  /  /:/~/::\    /  /:/    /__/::\      /  /:/  \:\   _____\__\:\ "
echo " /__/:/ /:/\:\ \__\/\:\__  /__/::::| \:\ /__/\  \__\:\ \  \:\ /  /:/ /__/:/ /:/\:\  /  /::\    \__\/\:\__  /__/:/ \__\:\ /__/::::::::\\"
echo " \  \:\/:/~/:/    \  \:\/\ \  \:\~~\__\/ \  \:\ /  /:/  \  \:\  /:/  \  \:\/:/__\/ /__/:/\:\      \  \:\/\ \  \:\ /  /:/ \  \:\~~\~~\/"
echo "  \  \::/ /:/      \__\::/  \  \:\        \  \:\  /:/    \  \:\/:/    \  \::/      \__\/  \:\      \__\::/  \  \:\  /:/   \  \:\  ~~~ "
echo "   \__\/ /:/       /__/:/    \  \:\        \  \:\/:/      \  \::/      \  \:\           \  \:\     /__/:/    \  \:\/:/     \  \:\     "
echo "     /__/:/        \__\/      \  \:\        \  \::/        \__\/        \  \:\           \__\/     \__\/      \  \::/       \  \:\    "
echo "     \__\/                     \__\/         \__\/                       \__\/                                 \__\/         \__\/    "
# Build SimulationAPI
echo "*** Building SimulationAPI - Begin ***"
cd Code/SimulationAPI/
./gradlew buildDocker -x test
echo "*** Building SimulationAPI - Completed ***"

# Return to Root
cd ../../


echo "              ___    _________                       .__          __             .___"
echo " /\           \  \   \_   ___ \  ____   _____ ______ |  |   _____/  |_  ____   __| _/"
echo " \/   ______   \  \  /    \  \/ /  _ \ /     \\\\____ \|  | _/ __ \   __\/ __ \ / __ | "
echo " /\  /_____/    )  ) \     \___(  <_> )  Y Y  \  |_> >  |_\  ___/|  | \  ___// /_/ | "
echo " \/            /  /   \______  /\____/|__|_|  /   __/|____/\___  >__|  \___  >____ | "
echo "              /__/           \/             \/|__|             \/          \/     \/ "
