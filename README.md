## Usage:
`java -jar scraper-assembly-0.1.jar <number of pages (optional)> <-Dconfig.file=path/to/config-file (optional)>`


## Configuration:
Default configuration is stored in file refrence.conf and contains following, self explanatory (hope so) entries:
- output-path=./output.json
- num-of-pages=1
- metrics-enabled=true

In theory -Dconfig.file=path/to/config-file should override this config but right now it is not working as it should

## Building jar:    
`sbt assembly` creates soc alled fatjar in target directory