const sass = require('sass');
const fs = require('fs');
const path = require('path');
const { program } = require('commander');

program.version('1.0.0');

program.option('-u, --unminified', 'genera foglio di stile non compresso');
program.option('-c, --clean', 'elimina i file css compilati');

program.parse(process.argv);

const options = program.opts();

const OUT_DIR = path.join('site', 'WebContent', 'assets', 'css');

const INPUT_DIR = path.join(
	__dirname,
	'..',
	'site',
	'WebContent',
	'assets',
	'scss'
);

const main = () => {
	if (!folderExists(OUT_DIR)) {
		fs.mkdirSync(OUT_DIR);
	}

	if (options.clean) {
		fs.readdirSync(OUT_DIR).forEach((file) => {
			fs.unlinkSync(path.join(OUT_DIR, file));
		});
		return;
	}

	const searchInFolder = (folder) => {
		fs.readdirSync(folder).forEach((file) => {
			const filePath = path.join(folder, file);
			if (fs.statSync(filePath).isDirectory()) {
				searchInFolder(filePath);
			} else if (filePath.endsWith('.scss')) {
				const outputFile = path.join(
					OUT_DIR,
					file.replace('.scss', '.css')
				);
				compileCss(filePath, outputFile, true);
				if (options.unminified) {
					compileCss(
						filePath,
						outputFile.replace('.css', '.unminified.css'),
						false
					);
				}
			}
		});
	};
	searchInFolder(INPUT_DIR);
};

const compileCss = (inputFile, outputFile, minified) => {
	sass.render(
		{ file: inputFile, outputStyle: minified ? 'compressed' : 'expanded' },
		(err, result) => {
			if (err) {
				console.error('⛔ Compile error ⛔');
				console.error(err.message);
				process.exit(-1);
			}

			fs.writeFile(outputFile, result.css, (err) => {
				if (err) {
					console.log(err);
					process.exit(-2);
				} else {
					console.log(
						`✅ Compiled ${inputFile} ${
							minified ? 'compressed' : 'expanded'
						} version`
					);
					return;
				}
			});
		}
	);
};

const folderExists = (path) => {
	try {
		fs.accessSync(path);
		return true;
	} catch (e) {
		return false;
	}
};

main();
