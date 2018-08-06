const path = require('path');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const merge = require('webpack-merge');
const webpack = require('webpack');
const ProgressPlugin = require('webpack/lib/ProgressPlugin');
const {AngularCompilerPlugin} = require('@ngtools/webpack');

module.exports = {
    entry: "./src/main/js/main.ts",
    output: {
        path: path.resolve(__dirname, "build/resources/main/static/"),
        filename: "main.js"
    },
    module: {
        rules: [
            {
                test: /\.ts$/,
                loader: '@ngtools/webpack',
                exclude: [/\.(spec|e2e)\.ts$/, /node_modules/],
            },
            {
                test: /\.ts$/,
                loader: 'null-loader',
                include: [/\.(spec|e2e)\.ts$/],
            },
            {
                test: /\.html$/,
                use: [{
                    loader: 'html-loader',
                }],
            },

        ]
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.jsx']
    },
    plugins: [
        new MiniCssExtractPlugin({
            // Options similar to the same options in webpackOptions.output
            // both options are optional
            filename: "[name].css",
            chunkFilename: "[id].css"
        }),
        new ProgressPlugin(),
        new AngularCompilerPlugin({
            platform: 0,
            entryModule: path.resolve(__dirname, 'src/main/js/app/app.module#AppModule'),
            sourceMap: true,
            tsConfigPath: path.resolve(__dirname, 'tsconfig.json'),
            skipCodeGeneration: true,
        })
    ]
};