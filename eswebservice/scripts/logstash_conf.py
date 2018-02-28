import sys
import argparse
import csv

def process_layout(layout_filename, logstash_conf_file):
    with open(layout_filename) as layout_file:
        field_names=['FIELD_POSITION','FIELD_NAME','TYPE','SIZE']
        reader = csv.DictReader(layout_file, delimiter=',',
                                quotechar=',', quoting=csv.QUOTE_NONE)
        #Read the fields from the first line.
        next(reader)
        fields_str= ''
        for layout_row in reader:
            fields_str = fields_str + '"' + layout_row['FIELD_NAME'] + '",'
        print(fields_str)
        for layout_row in reader:
            if layout_row['TYPE'] == 'NUMERIC':
                print('mutate {{convert => ["{}", "integer"]}}'.format(layout_row['FIELD_NAME']))

def main(argv):
    parser = argparse.ArgumentParser(description="ESBA Logstash Config File generator")
    parser.add_argument("-i", "--input", dest="layout_file", help="The ESBA layout file")
    parser.add_argument("-o", "--output", dest="logstash_conf_file", help="The logstash configuration file")

    args = parser.parse_args()
    print(args.layout_file, args.logstash_conf_file)
    process_layout(args.layout_file, args.logstash_conf_file)

if __name__ == "__main__":
    main(sys.argv)
